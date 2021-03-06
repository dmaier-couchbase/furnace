package com.tinkerpop.furnace.algorithms.vertexcentric.programs.ranking;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.furnace.algorithms.vertexcentric.GraphMemory;
import com.tinkerpop.furnace.algorithms.vertexcentric.VertexProgram;
import com.tinkerpop.furnace.util.VertexQueryBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class DegreeRankProgram implements VertexProgram {

    protected VertexQueryBuilder degreeQuery = new VertexQueryBuilder().direction(Direction.IN);
    private static final Map<String, KeyType> computeKeys = new HashMap<String, KeyType>();

    public static final String DEGREE = DegreeRankProgram.class.getName() + ".degree";

    static {
        computeKeys.put(DEGREE, KeyType.CONSTANT);
    }


    protected DegreeRankProgram() {

    }

    public Map<String, KeyType> getComputeKeys() {
        return computeKeys;
    }

    public void setup(final GraphMemory graphMemory) {

    }

    public void execute(final Vertex vertex, final GraphMemory graphMemory) {
        long degree = degreeQuery.build(vertex).count();
        vertex.setProperty(DEGREE, degree);
    }

    public boolean terminate(final GraphMemory graphMemory) {
        return true;
    }

    public static Builder create() {
        return new Builder();
    }

    //////////////////////////////

    public static class Builder {

        private final DegreeRankProgram vertexProgram = new DegreeRankProgram();

        public Builder degreeQuery(final VertexQueryBuilder degreeQuery) {
            this.vertexProgram.degreeQuery = degreeQuery;
            return this;
        }

        public DegreeRankProgram build() {
            return this.vertexProgram;
        }
    }
}
