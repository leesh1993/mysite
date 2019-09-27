package kr.co.itcen.mysite.action.board;

import kr.co.itcen.web.mvc.Action;
import kr.co.itcen.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {

		Action action = null;
		if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if("write".equals(actionName)) {
			action = new WriteAction();
		} else if("viewform".equals(actionName)) {
			action = new ViewFormAction();
		} else if("view".equals(actionName)) {
			action = new ViewAction();
		} else if("modifyform".equals(actionName)) {
			action = new ModifyFromAction();
		} else if("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if("search".equals(actionName)) {
			action = new SearchAction();
		} else if("boardreplyform".equals(actionName)) {
			action = new BoardReplyFormAction();
		} else if("boardreply".equals(actionName)) {
			action = new BoardReplyAction();
		}  else {
			/* default(list)*/
			action = new ListFormAction();
		}
		return action;
		
		
	}

}
