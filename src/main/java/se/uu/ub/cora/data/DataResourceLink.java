/*
 * Copyright 2015, 2016 Uppsala University Library
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

import java.util.ArrayList;
import java.util.List;

public final class DataResourceLink extends DataGroup implements DataLink {

	private List<Action> actions = new ArrayList<>();

	private DataResourceLink(String nameInData) {
		super(nameInData);
	}

	public DataResourceLink(DataGroup dataGroup) {
		super(dataGroup.getNameInData());
		addResourceLinkChildren(dataGroup);
		setRepeatId(dataGroup.getRepeatId());
	}

	private void addResourceLinkChildren(DataGroup dataGroup) {
		DataElement streamId = dataGroup.getFirstChildWithNameInData("streamId");
		addChild(streamId);
		DataElement fileName = dataGroup.getFirstChildWithNameInData("filename");
		addChild(fileName);
		DataElement fileSize = dataGroup.getFirstChildWithNameInData("filesize");
		addChild(fileSize);
		DataElement mimeType = dataGroup.getFirstChildWithNameInData("mimeType");
		addChild(mimeType);
	}

	public static DataResourceLink withNameInData(String nameInData) {
		return new DataResourceLink(nameInData);
	}

	@Override
	public void addAction(Action action) {
		actions.add(action);
	}

	@Override
	public List<Action> getActions() {
		return actions;
	}

	public static DataResourceLink fromDataGroup(DataGroup dataGroup) {
		return new DataResourceLink(dataGroup);
	}
}
