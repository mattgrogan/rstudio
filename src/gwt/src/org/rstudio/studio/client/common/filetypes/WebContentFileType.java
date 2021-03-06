/*
 * WebContentFileType.java
 *
 * Copyright (C) 2009-12 by RStudio, Inc.
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
package org.rstudio.studio.client.common.filetypes;

import java.util.HashSet;

import com.google.gwt.resources.client.ImageResource;

import org.rstudio.core.client.command.AppCommand;
import org.rstudio.studio.client.common.reditor.EditorLanguage;
import org.rstudio.studio.client.workbench.commands.Commands;

public class WebContentFileType extends TextFileType
{
   WebContentFileType(String id,
                      String label,
                      EditorLanguage editorLanguage,
                      String defaultExtension,
                      ImageResource icon,
                      boolean isMarkdown)
   {
      super(id, 
            label, 
            editorLanguage, 
            defaultExtension,
            icon,
            true,    // word-wrap
            false, 
            isMarkdown, // allow code execution in markdown 
            false, 
            false,
            true,    // preview-html
            false,
            false, 
            false,
            false,
            true,
            false,
            false);
      
      isMarkdown_ = isMarkdown;
   }
   
   @Override
   public HashSet<AppCommand> getSupportedCommands(Commands commands)
   {
      HashSet<AppCommand> result = super.getSupportedCommands(commands);
      if (isMarkdown_)
         result.add(commands.markdownHelp());
      return result;
   }
   
   private final boolean isMarkdown_;
}