package jp.ne.sakura.sync.web.mepper;

import java.util.List;

import org.jboss.logging.Param;

import jp.ne.sakura.sync.web.entity.Employee;
import jp.ne.sakura.sync.web.entity.EmployeeExample;
import jp.ne.sakura.sync.web.model.CustomEmployeeModel;

public interface EmployeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbg.generated Tue Mar 13 14:45:42 JST 2018
     */
    long countByExample(EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbg.generated Tue Mar 13 14:45:42 JST 2018
     */
    int deleteByExample(EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbg.generated Tue Mar 13 14:45:42 JST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbg.generated Tue Mar 13 14:45:42 JST 2018
     */
    int insert(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbg.generated Tue Mar 13 14:45:42 JST 2018
     */
    int insertSelective(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbg.generated Tue Mar 13 14:45:42 JST 2018
     */
    List<Employee> selectByExample(EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbg.generated Tue Mar 13 14:45:42 JST 2018
     */
    Employee selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbg.generated Tue Mar 13 14:45:42 JST 2018
     */
    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbg.generated Tue Mar 13 14:45:42 JST 2018
     */
    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbg.generated Tue Mar 13 14:45:42 JST 2018
     */
    int updateByPrimaryKeySelective(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbg.generated Tue Mar 13 14:45:42 JST 2018
     */
    int updateByPrimaryKey(Employee record);

    /**
     * 役職テーブルと結合し役職名と含めた従業員情報を取得する.
     * @param from 取得開始従業員ID
     * @param to 取得終了従業員ID
     * @return 従業員リスト
     */
    List<CustomEmployeeModel> getEmpWithPositionName(@Param("from")int from,@Param("to")int to);

}