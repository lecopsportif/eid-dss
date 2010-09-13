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

package be.fedict.eid.dss.model;

import java.security.cert.X509Certificate;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.fedict.eid.dss.spi.SignatureStatus;

/**
 * A document repository based on HTTP session storage.
 * 
 * @author Frank Cornelis
 * 
 */
public class DocumentRepository {

	private static final Log LOG = LogFactory.getLog(DocumentRepository.class);

	public static final String DOCUMENT_SESSION_ATTRIBUTE = DocumentRepository.class
			.getName() + ".document";

	public static final String DOCUMENT_CONTENT_TYPE_SESSION_ATTRIBUTE = DocumentRepository.class
			.getName() + ".DocumentContentType";

	public static final String SIGNED_DOCUMENT_SESSION_ATTRIBUTE = DocumentRepository.class
			.getName() + ".signedDocument";

	public static final String TARGET_SESSION_ATTRIBUTE = DocumentRepository.class
			.getName() + ".target";

	public static final String SIGNATURE_STATUS_SESSION_ATTRIBUTE = DocumentRepository.class
			.getName() + ".status";

	public static final String SIGNER_CERTIFICATE_SESSION_ATTRIBUTE = DocumentRepository.class
			.getName() + ".certificate";

	public final HttpSession httpSession;

	public DocumentRepository(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	public void reset() {
		this.httpSession.removeAttribute(DOCUMENT_SESSION_ATTRIBUTE);
		this.httpSession.removeAttribute(SIGNED_DOCUMENT_SESSION_ATTRIBUTE);
		this.httpSession.removeAttribute(TARGET_SESSION_ATTRIBUTE);
		this.httpSession.removeAttribute(SIGNATURE_STATUS_SESSION_ATTRIBUTE);
		this.httpSession.removeAttribute(SIGNER_CERTIFICATE_SESSION_ATTRIBUTE);
	}

	public void setDocument(byte[] document) {
		this.httpSession.setAttribute(DOCUMENT_SESSION_ATTRIBUTE, document);
	}

	public byte[] getDocument() {
		byte[] document = (byte[]) this.httpSession
				.getAttribute(DOCUMENT_SESSION_ATTRIBUTE);
		return document;
	}

	public void setDocumentContentType(String contentType) {
		LOG.debug("set document content type: " + contentType);
		this.httpSession.setAttribute(DOCUMENT_CONTENT_TYPE_SESSION_ATTRIBUTE,
				contentType);
	}

	public String getDocumentContentType() {
		String contentType = (String) this.httpSession
				.getAttribute(DOCUMENT_CONTENT_TYPE_SESSION_ATTRIBUTE);
		return contentType;
	}

	public void setSignedDocument(byte[] signedDocument) {
		this.httpSession.setAttribute(SIGNED_DOCUMENT_SESSION_ATTRIBUTE,
				signedDocument);
	}

	public byte[] getSignedDocument() {
		byte[] signedDocument = (byte[]) this.httpSession
				.getAttribute(SIGNED_DOCUMENT_SESSION_ATTRIBUTE);
		return signedDocument;
	}

	public void setTarget(String target) {
		this.httpSession.setAttribute(TARGET_SESSION_ATTRIBUTE, target);
	}

	public String getTarget() {
		String target = (String) this.httpSession
				.getAttribute(TARGET_SESSION_ATTRIBUTE);
		return target;
	}

	public void setSignatureStatus(SignatureStatus signatureStatus) {
		this.httpSession.setAttribute(SIGNATURE_STATUS_SESSION_ATTRIBUTE,
				signatureStatus);
	}

	public SignatureStatus getSignatureStatus() {
		SignatureStatus signatureStatus = (SignatureStatus) this.httpSession
				.getAttribute(SIGNATURE_STATUS_SESSION_ATTRIBUTE);
		return signatureStatus;
	}

	public void setSignerCertificate(X509Certificate signerCertificate) {
		this.httpSession.setAttribute(SIGNER_CERTIFICATE_SESSION_ATTRIBUTE,
				signerCertificate);
	}

	public X509Certificate getSignerCertificate() {
		X509Certificate signerCertificate = (X509Certificate) this.httpSession
				.getAttribute(SIGNER_CERTIFICATE_SESSION_ATTRIBUTE);
		return signerCertificate;
	}
}