/*
 * eID Digital Signature Service Project.
 * Copyright (C) 2009 FedICT.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version
 * 3.0 as published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, see 
 * http://www.gnu.org/licenses/.
 */

package be.fedict.eid.dss.portal.control.bean;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.bouncycastle.util.encoders.Base64;
import org.jboss.ejb3.annotation.LocalBinding;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.international.LocaleSelector;
import org.jboss.seam.log.Log;

import be.fedict.eid.dss.portal.control.XMLRequest;

@Stateful
@Name("xmlRequest")
@LocalBinding(jndiBinding = "fedict/eid/dss/portal/XMLRequestBean")
public class XMLRequestBean implements XMLRequest {

	@Logger
	private Log log;

	@In
	private LocaleSelector localeSelector;

	private String document;

	@Out(value = "target", scope = ScopeType.SESSION, required = false)
	private String target;

	@Out(value = "SignatureRequest", scope = ScopeType.SESSION, required = false)
	private String signatureRequest;

	@Out(value = "language", scope = ScopeType.SESSION, required = false)
	private String language;

	@Remove
	@Destroy
	@Override
	public void destroy() {
		this.log.debug("destroy");
	}

	@Override
	public String getDocument() {
		return this.document;
	}

	@Override
	public void setDocument(String document) {
		this.document = document;
	}

	@Override
	public String submit() {
		this.log.debug("submit");
		this.signatureRequest = new String(Base64.encode(this.document
				.getBytes()));

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		String requestContextPath = externalContext.getRequestContextPath();
		this.target = requestContextPath + "/post-response";

		this.language = this.localeSelector.getLanguage();

		return "success";
	}
}