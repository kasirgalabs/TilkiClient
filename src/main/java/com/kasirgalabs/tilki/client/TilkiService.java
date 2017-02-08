/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.tilki.client;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author rootg
 */
public class TilkiService<V> extends Service<V> {

    private String taskName;

    public TilkiService(String taskName) {
        this.taskName = taskName;
    }

    @Override
    protected Task<V> createTask() {
        TaskFactory<V> taskFactory = new TaskFactory<>();
        return taskFactory.getTask(taskName);
    }
}