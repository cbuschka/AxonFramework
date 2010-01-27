/*
 * Copyright (c) 2010. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.core.eventhandler;

import org.axonframework.core.Event;

/**
 * Interface to a policy definition for concurrent event handling.
 * <p/>
 * Some implementations are provided by default: <ul> <li>{@link SequentialPolicy}: Default policy. Requires that all
 * events are handled in the order they arrive at the event handler. This also means that at most 1 thread is processing
 * events for this handler at any time. <li>{@link FullConcurrencyPolicy}: Allows each event to be handled independently
 * of any other events. Event processing will typically start in the same order the events were dispatched in.
 * <li>{@link SequentialPerAggregatePolicy}: Will force events generated by the same aggregate to be handled
 * sequentially. At most one thread will be processing events of a single aggregate at any time</ul>
 *
 * @author Allard Buijze
 * @since 0.3
 */
public interface EventSequencingPolicy {

    /**
     * Returns the sequence identifier for the given <code>event</code>. When two events have the same identifier (as
     * defined by their equals method), they will be executed sequentially. A <code>null</code> value indicates that
     * there are no sequencing requirements for the handling of this event.
     *
     * @param event the event for which to get the sequencing identifier
     * @return a sequence identifier for the given event
     */
    Object getSequenceIdentifierFor(Event event);
}
