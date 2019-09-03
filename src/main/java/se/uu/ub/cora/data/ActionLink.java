/*
 * Copyright 2015, 2018 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.uu.ub.cora.data;

public final class ActionLink implements Data {

	private DataGroup body;
	private Action action;
	private String url;
	private String requestMethod;
	private String accept;
	private String contentType;

	private ActionLink(Action action) {
		this.action = action;
	}

	public static ActionLink withAction(Action action) {
		return new ActionLink(action);
	}

	public Action getAction() {
		return action;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getURL() {
		return url;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getAccept() {
		return accept;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setBody(DataGroup body) {
		this.body = body;
	}

	public DataGroup getBody() {
		return body;
	}
}
