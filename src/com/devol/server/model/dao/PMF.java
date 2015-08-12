package com.devol.server.model.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class PMF {

	private static final PersistenceManagerFactory PMF = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	private PMF() {

	}

	public static PersistenceManagerFactory getPMF() {
		return PMF;
	}

}
