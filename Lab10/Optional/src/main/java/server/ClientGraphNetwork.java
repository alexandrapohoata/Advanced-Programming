/*
 * (C) Copyright 2003-2021, by Barak Naveh and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * See the CONTRIBUTORS.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the
 * GNU Lesser General Public License v2.1 or later
 * which is available at
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1-standalone.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR LGPL-2.1-or-later
 */

package server;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple introduction to using JGraphT.
 *
 * @author Barak Naveh
 */
public class ClientGraphNetwork {
    private final List<ClientThread> clientThreads;

    public ClientGraphNetwork(List<ClientThread> clientThreads) {
        this.clientThreads = clientThreads;
    }

    public List<Graph<ClientThread, DefaultEdge>> createConnections() {
        List<Graph<ClientThread, DefaultEdge>> network = new ArrayList<>();

        for (ClientThread client : clientThreads) {
            Graph<ClientThread, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

            for (ClientThread friend : client.getMyFriends()) {
                if (client == friend)
                    continue;

                g.addVertex(friend);
                g.addEdge(client, friend);
            }

            network.add(g);
        }
        return network;
    }
}