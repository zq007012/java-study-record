package com.zq.dao;

import com.zq.pojo.Course;
import com.zq.pojo.Course_Lesson;
import com.zq.pojo.Course_Section;

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
     * 更新<code>course_lesson</code>表中课时信息
     * <p>需要更新的字段是: <code>course_id, section_id, theme, duration, is_free, order_num, update_time</code></p>
     * <p>需要满足的条件: <code>id=?</code></p>
     * @param lesson
     * @return
     */
    int updateLesson(Course_Lesson lesson);

    /**
     * 根据章节id查询该章节下的所有课时
     *
     * @param sectionId 章节id
     * @return
     */
    List<Course_Lesson> findLessonsBySectionId(int sectionId);

    /**
     * 根据课程id获取该课程下的所有章节, 同时每个章节都携带有课时信息
     *
     * @param courseId 课程id
     * @return
     */
    List<Course_Section> findSectionsWithLessonsByCourseId(int courseId);

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
    int saveSection(Course_Section courseSection);

    /**
     * 更新course_section表中的一条记录, 更新的字段有
     * <p><code>section_name,description,order_num,update_time</code></p>
     * <p>这条记录要满足<code>id = ?</code></p>
     * @param courseSection
     * @return
     */
    int updateSection(Course_Section courseSection);

    /**
     * 修改课程状态, 修改的字段有
     * <p><code>status, update_time</code></p>
     * <p>条件是: <code>id = ? </code></p>
     * <p>status  --- 0:隐藏；1：待更新；2：已发布</p>
     * @param section
     * @return
     */
    int updateSectionStatus(Course_Section section);

    /**
     * 保存新课时
     * <p>保存的字段有<code>course_id, section_id, theme, duration, is_free, order_num,  create_time, update_time, status</code></p>
     * <p>status : 课时状态,0-隐藏，1-未发布，2-已发布</p>
     * <p>is_free : 是否免费, 0-否, 1-是</p>
     * @param lesson
     * @return
     */
    int saveLesson(Course_Lesson lesson);

    /**
     * 根据章节id获取章节信息
     * <p>获取的字段有<code>id, section_name</code></p>
     * @param sectionId
     * @return
     */
    Course_Section findSectionById(int sectionId);
}