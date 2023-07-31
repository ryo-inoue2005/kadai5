package form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import omikuji.Omikuji;

/**
 * PastListFormクラス. <br>
 * PastListFormクラスは、PastListDtoを格納するリストを保管します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class PastListForm extends ActionForm {

	private List<Omikuji> listOmikuji;

	/**
	 * おみくじリストの取得
	 */
	public List<Omikuji> getListOmikuji() {
		return listOmikuji;
	}

	/**
	 * おみくじリストのセット
	 */
	public void setListOmikuji(List<Omikuji> listOmikuji) {
		this.listOmikuji = listOmikuji;
	}
}
