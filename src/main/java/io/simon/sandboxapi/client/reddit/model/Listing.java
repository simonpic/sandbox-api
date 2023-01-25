package io.simon.sandboxapi.client.reddit.model;

import java.util.List;

public class Listing {
    private String after;
    private int dist;
    private List<RedditObject<RedditThread>> children;

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public List<RedditObject<RedditThread>> getChildren() {
        return children;
    }

    public void setChildren(List<RedditObject<RedditThread>> children) {
        this.children = children;
    }
}
