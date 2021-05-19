# VScode设置优化

VScode的全称是Visual Studio Code

## 一. 插件

| 插件名称                  | 说明 |
| ------------------------- | ---- |
| CSS Peek                  |      |
| ESLint                    |      |
| HTML Snippets             |      |
| LIve Server               |      |
| Material Icon Theme       |      |
| Opent In Brower           |      |
| Prettier - Code formmater |      |
| Trailing Space            |      |
| vscode-icons              |      |
| HTML CSS Support          |      |
| code runner               |      |

## 二. 自定义设置

```json
{
    "window.zoomLevel": 0.75, //窗口缩放
    "editor.fontSize": 20, //字体大小
    "editor.renderControlCharacters": true, //渲染控制字符
    "editor.renderWhitespace": "boundary", //渲染空白字符
    "editor.minimap.enabled": false, //主题
    "git.enabled": false, //关闭git
    "workbench.iconTheme": "vscode-icons", //主题背景
    "editor.formatOnSave": true, //保存时格式化
    "editor.suggestFontSize": 18, //
    "workbench.colorCustomizations": {
        "editor.background": "#d8d3d311", //编辑器的背景色
        "sideBar.background": "#d8d3d36a", //侧边栏的背景色
        "editorBracketMatch.background": "#af188e63", //各种括号的背景色
        "editorBracketMatch.border": "#680653e8", //高亮显示背景色时的边框
        "editor.selectionBackground": "#e0dd2956", //用户选中代码段的颜色
        "editor.selectionHighlightBorder": "#772e6be0", //选择内容的边框颜色
        "editor.selectionHighlightBackground": "#e0dd2956", //选择内容的背景颜色
        "editor.foldBackground": "#e0dd2991", //折叠区域的背景颜色
        "editorIndentGuide.activeBackground": "#141414dc", //编辑器活动缩进参考线的颜色
        "editorIndentGuide.background": "#14141457", //编辑器活动缩进参考线的颜色
        "editor.lineHighlightBackground": "#d8d3d31a", //光标所在行高亮内容的背景颜色
        "editor.lineHighlightBorder": "#610661ee", //光标所在行四周边框的背景颜色
        "editor.findMatchBackground": "#e0dd2956", //当前搜索匹配的颜色
        "editor.findMatchBorder": "#1f1e04ee", //当前搜索匹配的边框的颜色
        "editor.findMatchHighlightBackground": "#e0dd2956", //其他搜索匹配项的颜色
        "editor.findRangeHighlightBackground": "#e0dd2956", //限制搜索范围的颜色
        //可以将鼠标放到下面的色号上根据自己的偏好进行选择
        "terminal.foreground": "#011101",
        "terminal.background": "#e9e3e3"
    },
    "liveServer.settings.donotShowInfoMsg": true,
    "editor.linkedEditing": true,
    "open-in-browser.default": "chrome",
    "editor.showFoldingControls": "always",
    "explorer.confirmDelete": false,
    "liveServer.settings.CustomBrowser": "chrome",
    "[jsonc]": {
        "editor.defaultFormatter": "vscode.json-language-features"
    },
    "editor.wordWrap": "on",
    "[html]": {
        "editor.defaultFormatter": "esbenp.prettier-vscode"
    },
    "explorer.confirmDragAndDrop": false,
    "liveServer.settings.donotVerifyTags": true,
    "vsicons.dontShowNewVersionMessage": true,
    "workbench.colorTheme": "Default Light+",
    "terminal.integrated.fontSize": 18,
    "terminal.integrated.lineHeight": 1.0,
    "terminal.integrated.letterSpacing": 0.1,
    "terminal.integrated.fontFamily": "Lucida Console",
    "terminal.integrated.cursorStyle": "line",
    "terminal.integrated.cursorWidth": 2,
    "terminal.integrated.shell.windows": "cmd.exe",
}
```

## 三. 自定义快捷键

| 操作                          | 快捷键   |
| ----------------------------- | -------- |
| openInDefaultBrowser          | alt + b  |
| copyLinesDownAction           | ctrl + d |
| format                        | alt + f  |
| go to next problem            | alt + d  |
| extension.liveServer.goOnline | alt + a  |
| files.save                    | alt + s  |
| open in terminal              | alt + t  |
| code runner                   | alt + e  |

```json
// 将键绑定放在此文件中以覆盖默认值auto[]
[
    {
        "key": "alt+b",
        "command": "extension.openInDefaultBrowser"
    },
    {
        "key": "alt+b",
        "command": "-extension.openInDefaultBrowser"
    },
    {
        "key": "ctrl+d",
        "command": "-editor.action.addSelectionToNextFindMatch",
        "when": "editorFocus"
    },
    {
        "key": "ctrl+d",
        "command": "editor.action.copyLinesDownAction",
        "when": "editorTextFocus && !editorReadonly"
    },
    {
        "key": "alt+f",
        "command": "notebook.formatCell",
        "when": "editorHasDocumentFormattingProvider && editorTextFocus && inCompositeEditor && notebookEditable && !editorReadonly && activeEditor == 'workbench.editor.notebook'"
    },
    {
        "key": "alt+f",
        "command": "editor.action.formatDocument",
        "when": "editorHasDocumentFormattingProvider && editorTextFocus && !editorReadonly && !inCompositeEditor"
    },
    {
        "key": "alt+f",
        "command": "editor.action.formatDocument.none",
        "when": "editorTextFocus && !editorHasDocumentFormattingProvider && !editorReadonly"
    },
    {
        "key": "alt+f",
        "command": "notebook.format",
        "when": "notebookEditable && !editorTextFocus && activeEditor == 'workbench.editor.notebook'"
    },
    {
        "key": "alt+f",
        "command": "filesExplorer.findInFolder",
        "when": "explorerResourceIsFolder && explorerViewletVisible && filesExplorerFocus && !inputFocus"
    },
    {
        "key": "alt+a",
        "command": "extension.liveServer.goOnline",
        "when": "editorTextFocus"
    },
    {
        "key": "alt+d",
        "command": "editor.action.marker.next",
        "when": "editorFocus"
    },
    {
        "key": "alt+f8",
        "command": "-editor.action.marker.next",
        "when": "editorFocus"
    },
    {
        "key": "alt+oem_2",
        "command": "editor.action.blockComment",
        "when": "editorTextFocus && !editorReadonly"
    },
    {
        "key": "alt+t",
        "command": "openInTerminal"
    },
    {
        "key": "alt+e",
        "command": "code-runner.run"
    },
    {
        "key": "alt+s",
        "command": "workbench.action.files.save"
    }
]
```

