/*
 * RmdFileOption.java
 *
 * Copyright (C) 2009-14 by RStudio, Inc.
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.studio.client.rmarkdown.ui;

import org.rstudio.core.client.widget.FileChooserTextBox;
import org.rstudio.studio.client.rmarkdown.model.RmdTemplateFormatOption;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;

public class RmdFileOption extends RmdNullableOption
{
   public RmdFileOption(RmdTemplateFormatOption option, String initialValue)
   {
      super(option, initialValue);
      defaultValue_ = option.getDefaultValue();

      HTMLPanel panel = new HTMLPanel("");

      if (option.isNullable())
      {
         panel.add(nonNullCheckBox());
      }
      else
      {
         panel.add(new InlineLabel(option.getUiName() + ": "));
      }
      
      fileChooser_ = new FileChooserTextBox("", null);
      if (!initialValue.equals("null"))
         fileChooser_.setText(initialValue);
      fileChooser_.getElement().getStyle().setMarginLeft(20, Unit.PX);
      fileChooser_.getElement().getStyle().setMarginTop(3, Unit.PX);
      panel.add(fileChooser_);

      updateNull();

      initWidget(panel);
   }

   @Override
   public boolean valueIsDefault()
   {
      if (valueIsNull() && defaultValue_.equals("null"))
         return true;
      return getValue() == defaultValue_;
   }

   @Override
   public String getValue()
   {
      if (valueIsNull())
         return null;
      return fileChooser_.getText();
   }
   
   @Override
   public void updateNull()
   {
      fileChooser_.setEnabled(!valueIsNull());
   }

   FileChooserTextBox fileChooser_;
   CheckBox nonNullCheck_;
   String defaultValue_;
}