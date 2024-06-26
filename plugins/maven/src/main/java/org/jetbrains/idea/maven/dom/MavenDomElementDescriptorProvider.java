/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.idea.maven.dom;

import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.xml.XmlElementDescriptorProvider;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.impl.DomManagerImpl;
import com.intellij.xml.XmlElementDescriptor;
import com.intellij.xml.impl.schema.AnyXmlElementDescriptor;
import org.jetbrains.idea.maven.dom.model.MavenDomConfiguration;

public final class MavenDomElementDescriptorProvider implements XmlElementDescriptorProvider {
  @Override
  public XmlElementDescriptor getDescriptor(XmlTag tag) {
    DomElement domElement = DomManagerImpl.getDomManager(tag.getProject()).getDomElement(tag);
    if (domElement == null) {
      PsiElement configuration =
        PsiTreeUtil.findFirstParent(tag, element -> element instanceof XmlTag && "configuration".equals(((XmlTag)element).getName()));
      if (configuration instanceof XmlTag) {
        DomElement configurationElement = DomManagerImpl.getDomManager(tag.getProject()).getDomElement((XmlTag)configuration);
        if (configurationElement instanceof MavenDomConfiguration &&
            configurationElement.getGenericInfo().getFixedChildrenDescriptions().isEmpty()) {
          return new AnyXmlElementDescriptor(null, null);
        }
      }
    }
    return MavenDomElementDescriptorHolder.getInstance(tag.getProject()).getDescriptor(tag);
  }
}
