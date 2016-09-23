/**
 * Copyright 2012 Cheng Wei, Robert Taylor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.robobinding.gallery.presentationmodel;

import android.util.SparseBooleanArray;

import org.robobinding.annotation.DependsOnStateOf;
import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.gallery.invocationlog.PublicMethodInvocationLog;
import org.robobinding.gallery.model.Strings;
import org.robobinding.gallery.model.adapterview.SampleStrings;
import org.robobinding.gallery.model.listview.SampleStringsFooter;
import org.robobinding.gallery.model.view.BooleanVisibility;
import org.robobinding.gallery.model.view.IntegerVisibility;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

import java.util.List;

/**
 * @author Cheng Wei
 * @version $Revision: 1.0 $
 * @since 1.0
 */
@PresentationModel
public class ListViewPresentationModel implements HasPresentationModelChangeSupport, PublicMethodInvocationLog {
    private static final String FOOTER_INTEGER_VISIBILITY = "footerIntegerVisibility";

    private static final String HEADER_BOOLEAN_VISIBILITY = "headerBooleanVisibility";

    private final PresentationModelChangeSupport changeSupport;

    private BooleanVisibility headerBooleanVisibility;

    private IntegerVisibility footerIntegerVisibility;

    private SparseBooleanArray checkedItemPositions;
    private int checkedItemPosition;

    public ListViewPresentationModel() {
        changeSupport = new PresentationModelChangeSupport(this);
        headerBooleanVisibility = new BooleanVisibility();

        footerIntegerVisibility = new IntegerVisibility();

        checkedItemPositions = new SparseBooleanArray();
        checkedItemPositions.append(0, true);
        checkedItemPosition = 0;
    }

    //每个item指定一个PM，用于获取name
    @ItemPresentationModel(value = StringItemPresentationModel.class)
    public List<String> getStrings() {
        return SampleStrings.getSample();
    }

    public boolean getHeaderBooleanVisibility() {
        return headerBooleanVisibility.getValue();
    }

    public void changeHeaderVisibility() {
        headerBooleanVisibility.nextState();
        changeSupport.firePropertyChange(HEADER_BOOLEAN_VISIBILITY);
    }

    @DependsOnStateOf(HEADER_BOOLEAN_VISIBILITY)
    public String getHeaderBooleanVisibilityDescription() {
        return "Header " + headerBooleanVisibility.describe("visible", "invisible");
    }

    public SampleStringsFooter getFooter() {
        return SampleStringsFooter.getInstance();
    }

    public int getFooterIntegerVisibility() {
        return footerIntegerVisibility.getValue();
    }

    public void changeFooterVisibility() {
        footerIntegerVisibility.nextState();
        changeSupport.firePropertyChange(FOOTER_INTEGER_VISIBILITY);
    }

    @DependsOnStateOf(FOOTER_INTEGER_VISIBILITY)
    public String getFooterIntegerVisibilityDescription() {
        return "Footer " + footerIntegerVisibility.describe("visible", "invisible", "gone");
    }

    public int getCheckedItemPosition() {
        return checkedItemPosition;
    }

    public void setCheckedItemPosition(int checkedItemPosition) {
        this.checkedItemPosition = checkedItemPosition;
    }

    @DependsOnStateOf("checkedItemPosition")
    public String getDescriptionOfSelectedItem() {
        return "" + checkedItemPosition;
    }

    public SparseBooleanArray getCheckedItemPositions() {
        return checkedItemPositions;
    }

    public void setCheckedItemPositions(SparseBooleanArray checkedItemPositions) {
        this.checkedItemPositions = checkedItemPositions;
    }

    @DependsOnStateOf("checkedItemPositions")
    public String getDescriptionOfSelectedItems() {
        return Strings.toString(checkedItemPositions);
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }
}
