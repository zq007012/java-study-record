# 第XX章 对IDEA上的侧边栏Database进行配置

[toc]

## 一. 初始化

配置好后就可以在Database面板上实时地查看数据库的信息了, **最重要的是可以自动生成跟表对应的Entity类**

1. 与数据库建立连接: 打开IDEA上的Sidebar➡Database, 在Database面板上的空白处点击右键, 选择`New`➡`Data Source`➡`MySQL` , 按要求完善好数据库服务器的连接信息, 之后点击OK
2. 显示数据库中的表以及表的列这些概要(Schemas)信息: 在新建立的数据库连接上右键, 选择`Database Tools`➡`Manage Shown Schemas...`, 勾选这个数据库, 就能显示这些概要信息了.

## 二. 自动生成表对应的Entity类

- 选择全部要生成Entity类的表, 右键, 选择`Scripted Extensions`→`Generate POJOS.groovy`, 然后选择保存Entity类的包, 点击确定

- 可以选择`Scripted Extensions`→`Goto Scripted Directory`, 找到`Generate POJOS.groovy`文件, 在同一文件夹下创建一个`Generate POJOS_ZQ.groovy`文件对该文件进行以下自定义修改, **注意这个文件对中文的支持不太好, 所以自定义时不要出现汉字**

  - ```groovy
    import com.intellij.database.model.DasTable
    import com.intellij.database.util.Case
    import com.intellij.database.util.DasUtil
    
    /*
     * Available context bindings:
     *   SELECTION   Iterable<DasObject>
     *   PROJECT     project
     *   FILES       files helper
     */
    //-----------------------改 包  名--------------------------
    packageName = "com.zq.store.emp;"
    typeMapping = [
    		(~/(?i)int/)                      : "long",
    		(~/(?i)float|double|decimal|real/): "double",
    		(~/(?i)datetime|timestamp/)       : "java.sql.Timestamp",//时间对应的java类型换成了String
    		(~/(?i)date/)                     : "java.sql.Date",//时间对应的java类型换成了String
    		(~/(?i)time/)                     : "java.sql.Time",//时间对应的java类型换成了String
    		(~/(?i)/)                         : "String"
    ]
    
    FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    	SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
    }
    
    def generate(table, dir) {
    	def className = javaName(table.getName(), true)
    	def fields = calcFields(table)
    	//改编码, 解决乱码问题
    	new File(dir, className + ".java").withPrintWriter { out ->
    		generate(out, className, fields)
    	}
    }
    
    def generate(out, className, fields) {
    	//包名,
    	out.println "package $packageName"
    	out.println ""
    	//要导入的包
    	out.println ""
    	out.println "import java.io.Serializable;"
    	out.println "import java.sql.SQLException;"
    	out.println "import java.util.ArrayList;"
    	out.println "import java.util.List;"
    	//类名
    	out.println ""
    	out.println "public class $className implements Serializable{"
    	//成员变量
    	out.println ""
    	fields.each() {
    		if (it.annos != "") out.println "  ${it.annos}"
    		out.println "  private ${it.type} ${it.name};"
    	}
    	//空参构造方法
    	out.println ""
    	out.println "  public $className() {"
    	out.println "  }"
        
    	//getter和setter
    	out.println ""
    	fields.each() {
    		out.println ""
    		out.println "  public ${it.type} get${it.name.capitalize()}() {"
    		out.println "    return ${it.name};"
    		out.println "  }"
    		out.println ""
    		out.println "  public void set${it.name.capitalize()}(${it.type} ${it.name}) {"
    		out.println "    this.${it.name} = ${it.name};"
    		out.println "  }"
    
    	}
    	//类的结束括号
    	out.println "}"
    }
    
    def calcFields(table) {
    	DasUtil.getColumns(table).reduce([]) { fields, col ->
    		def spec = Case.LOWER.apply(col.getDataType().getSpecification())
    		def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
    		fields += [[
    				           name : javaName(col.getName(), false),
    				           type : typeStr,
    				           annos: ""]]
    	}
    }
    
    def javaName(str, capitalize) {
    	def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
    			.collect { Case.LOWER.apply(it).capitalize() }
    			.join("")
    			.replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    	capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
    }
    ```

  - 