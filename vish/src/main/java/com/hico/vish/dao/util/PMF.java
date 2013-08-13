package com.hico.vish.dao.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {
	private static final PMF INSTANCE = new PMF();
	private static final PersistenceManagerFactory PMFINSTANCE = JDOHelper.getPersistenceManagerFactory("customerPMF");

	private PMF() {
	}

	public static PMF getInstance() {
		return INSTANCE;
	}

	public PersistenceManagerFactory get() {
		return PMFINSTANCE;
	}

}
