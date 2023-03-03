package com.unifacisa.app;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.unifacisa.entities.Client;
import com.unifacisa.utils.ClientComparator;

public class ServiceControls {
	private PriorityQueue<Client> queue = new PriorityQueue<Client>(new ClientComparator());

	private List<Client> aux = new ArrayList<Client>();
	private int count = 0;
	
	public ServiceControls() {
		super();
	}
	
	public PriorityQueue<Client> getQueue() {
		return queue;
	}

	public void setQueue(PriorityQueue<Client> queue) {
		this.queue = queue;
	}

	/**
	 * @apiNote Adicionar cliente na fila de prioridade
	 * 
	 * @author Darllinson Azevedo
	 * 
	 * @param c Cliente para ser adicionado
	 */
	public void add(Client c) {
		this.queue.add(c);
	}
	
	/**
	 * @apiNote Chamar proximo cliente
	 * 
	 * @author Darllinson Azevedo
	 * 
	 * @return Proximo cliente na fila de prioridade
	 */
	public Client nextClient() {
		List<Client> queueToList = new ArrayList<Client>(this.queue);
		
		if (this.count == 3) {
			this.count = 0;
			
			for (Client client : queueToList) {
				if (client.getPriority() == 2) {
					this.queue.poll();
					this.aux.add(client);
				}
			}
			
			return this.queue.poll();
		} else {
			for (Client client : this.aux) {
				this.queue.add(client);
			}
			this.aux.clear();
			
			this.count++;
			return this.queue.poll();
		}
	}
}
