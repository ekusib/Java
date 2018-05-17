package jp.ne.sakura.sync.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ne.sakura.sync.web.entity.EmployeeExample;
import jp.ne.sakura.sync.web.mepper.EmployeeMapper;

@Service
public class DBOperationService {

	@Autowired
	public EmployeeMapper employee;

	/**
	 * 条件が一致するemployeeデータを削除する。
	 * @param name 従業員名
	 * @param mail メールアドレス
	 * @param positionNo 役職番号
	 */
	public void delete(String name, String mail, int positionNo){
			EmployeeExample example = new EmployeeExample();
			example.createCriteria()
			.andNameEqualTo(name)
			.andMailEqualTo(mail)
			.andPositionNoEqualTo(positionNo);
			employee.deleteByExample(example);
	}

}

