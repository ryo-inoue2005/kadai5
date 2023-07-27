package form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import dto.PastListDto;

/**
 * PastListFormクラス. <br>
 * PastListFormクラスは、PastListDtoを格納するリストを保管します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class PastListForm extends ActionForm {

	/** PastListDtoを格納するリスト */
	private List<PastListDto> pastList = new ArrayList<>();

	/**
	 * リストの取得
	 */
	public List<PastListDto> getPastList() {
		return pastList;
	}

	/**
	 * リストのセット
	 */
	public void setPastList(List<PastListDto> pastList) {
		this.pastList = pastList;
	}
}
