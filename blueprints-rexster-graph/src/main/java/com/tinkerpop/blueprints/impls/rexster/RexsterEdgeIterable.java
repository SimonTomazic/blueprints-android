package com.tinkerpop.blueprints.impls.rexster;

import java.util.Queue;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tinkerpop.blueprints.Edge;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
class RexsterEdgeIterable extends RexsterElementIterable<Edge> {

    public RexsterEdgeIterable(final String uri, final RexsterGraph graph) {
        super(uri, graph);
    }

    protected void fillBuffer(final Queue<Edge> queue, final int start, final int end) {
        final JSONObject object = RestHelper.get(this.uri + this.createSeparator() + RexsterTokens.REXSTER_OFFSET_START + RexsterTokens.EQUALS + start + RexsterTokens.AND + RexsterTokens.REXSTER_OFFSET_END + RexsterTokens.EQUALS + end);

        JSONArray array = object.optJSONArray(RexsterTokens.RESULTS);
        for (int ix = 0; ix < array.length(); ix++) {
            queue.add(new RexsterEdge(array.optJSONObject(ix), this.graph));
        }
    }
}