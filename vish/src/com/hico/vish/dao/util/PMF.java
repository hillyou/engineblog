package com.hico.vish.dao.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {
	private static final PersistenceManagerFactory PMFINSTANCE =JDOHelper.getPersistenceManagerFactory("transactions-optional");

	    private PMF() {}

	    public static PersistenceManagerFactory get() {
	        return PMFINSTANCE;
	    }
}
