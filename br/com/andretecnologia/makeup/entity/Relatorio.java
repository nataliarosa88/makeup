package br.com.andretecnologia.makeup.entity;

import javax.persistence.EntityManager;

import br.com.andretecnologia.makeup.factory.MakeupFactory;

public class Relatorio {

	public static void main(String[] args) {
		EntityManager em = new MakeupFactory().getEntityManager();
		SchedulingCreation.listSheduling(em);
	}

}
