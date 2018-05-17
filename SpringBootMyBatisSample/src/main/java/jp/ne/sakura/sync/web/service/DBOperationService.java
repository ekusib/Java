package jp.ne.sakura.sync.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ne.sakura.sync.web.entity.Employee;
import jp.ne.sakura.sync.web.mepper.EmployeeMapper;

@Service
public class DBOperationService {

	@Autowired
	public EmployeeMapper employee;

	/**
	 * 指定したidの範囲に一致したemployeeデータを取得する。(役職名)
	 * @param form 開始従業員ID
	 * @param to 終了従業員ID
	 * @return 従業員情報リスト
	 */
	public List<Employee> getEmpWithPositionName(int from, int to){
	List<Employee> empList = employee.getEmpWithPositionName(from, to);
	return empList;
	}

}

