package com.zq.dao;

import com.zq.pojo.Course;
import com.zq.pojo.Course_Lesson;
import com.zq.pojo.Course_Section;
import com.zq.service.CourseService;

import java.util.List;

/**
 * 课程内容相关的Dao接口
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/6 21:37
 */
public interface CourseContentDao {

    /**
     * 根据章节id查询该章节下的所有课时
     *
     * @param sectionId 章节id
     * @return
     */
    List<Course_Lesson> findCourseLessonsBySectionId(int sectionId);

    /**
     * 根据课程id获取该课程下的所有章节, 同时每个章节都携带有课时信息
     *
     * @param courseId 课程id
     * @return
     */
    List<Course_Section> findCourseSectionsWithLessonsByCourseId(int courseId);

    /**
     * 根据课程id获取该课程的<code>id, course_name</code>
     *
     * @param courseId
     * @return
     */
    Course findCourseById(int courseId);

    /**
     * 向course_section中保存一条章节记录, 保存的字段有
     * <p><code>section_name,description,order_num,STATUS,create_time,update_time</code></p>
     * <p>status --- 0:隐藏；1：待更新；2：已发布</p>
     * @param courseSection
     * @return
     */
    int saveCourseSection(Course_Section courseSection);

    /**
     * 更新course_section表中的一条记录, 更新的字段有
     * <p><code>section_name,description,order_num,update_time</code></p>
     * <p>这条记录要满足<code>id = ?</code></p>
     * @param courseSection
     * @return
     */
    int updateCourseSection(Course_Section courseSection);

    /**
     * 修改课程状态, 修改的字段有
     * <p><code>status, update_time</code></p>
     * <p>条件是: <code>id = ? </code></p>
     * <p>status  --- 0:隐藏；1：待更新；2：已发布</p>
     * @param section
     * @return
     */
    int updateCourseSectionStatus(Course_Section section);
}
