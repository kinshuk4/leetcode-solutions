package com.vaani.leetcode.graph;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

class ReconstructItineraryTest {
    @Test
    public void testIterativeSolution() {
        List<List<String>> tickets = List.of(List.of("MUC", "LHR"), List.of("JFK", "MUC"), List.of("SFO", "SJC"), List.of("LHR", "SFO"));
        List<String> result = List.of("JFK", "MUC", "LHR", "SFO", "SJC");
        Assert.assertEquals(result, new ReconstructItinerary.UsingDFSIterative().findItinerary(tickets));
    }
}