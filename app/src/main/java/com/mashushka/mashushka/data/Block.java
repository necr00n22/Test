package com.mashushka.mashushka.data;

import java.util.Date;
import java.util.List;

/**
 * Created by Mikhail Li (Jiub) on 25.07.2018.
 */

public class Block {
    private String title;
    private Type type;

    private long startDate;
    private long endDate;
    private String description;

    public Block(String title, Type type) {
        this.title = title;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {

        private String title;
        private Type type;
        private long startDate = -1;
        private long endDate = -1;
        private String description;

        public Builder(String title, Type type) {

            this.title = title;
            this.type = type;
        }

        public Builder setStartDate(long startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(long endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Block build() {
            Block block = new Block(title, type);
            block.startDate = startDate;
            block.endDate = endDate;
            block.description = description;

            return block;
        }

    }
}
