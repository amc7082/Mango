package com.serotonin.mango.vo.report;

import java.io.*;

import com.serotonin.ShouldNeverHappenException;
import com.serotonin.util.SerializationHelper;

public class ReportPointVO implements Serializable {
    private int pointId;
    private String colour;
    private boolean consolidatedChart;
    private String type;
    private String title;
    private String xLabel;
    private String yLabel;
    private String referenceLine;

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public boolean isConsolidatedChart() {
        return consolidatedChart;
    }

    public void setConsolidatedChart(boolean consolidatedChart) {
        this.consolidatedChart = consolidatedChart;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getXLabel() {
        return this.xLabel;
    }

    public void setXLabel(String xLabel) {
        this.xLabel = xLabel;
    }

    public String getYLabel() {
        return this.yLabel;
    }

    public void setYLabel(String yLabel) {
        this.yLabel = yLabel;
    }

    public String getReferenceLine() {
        return this.referenceLine;
    }

    public void setReferenceLine(String referenceLine) {
        this.referenceLine = referenceLine;
    }

    //
    //
    // Serialization
    //
    private static final long serialVersionUID = -1;
    private static final int version = 2;

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(version);

        out.writeInt(pointId);
        SerializationHelper.writeSafeUTF(out, colour);
        out.writeBoolean(consolidatedChart);
        SerializationHelper.writeSafeUTF(out, type);
        SerializationHelper.writeSafeUTF(out, title);
        SerializationHelper.writeSafeUTF(out, xLabel);
        SerializationHelper.writeSafeUTF(out, yLabel);
        SerializationHelper.writeSafeUTF(out, referenceLine);
    }

    private void readObject(ObjectInputStream in) throws IOException {
        int ver = in.readInt();

        // Switch on the version of the class so that version changes can be elegantly handled.
        if (ver == 1) {
            pointId = in.readInt();
            colour = SerializationHelper.readSafeUTF(in);
            consolidatedChart = true;
        }
        else if (ver == 2 || ver == 3) {
            pointId = in.readInt();
            colour = SerializationHelper.readSafeUTF(in);
            consolidatedChart = in.readBoolean();

            try {
                type = SerializationHelper.readSafeUTF(in);
                title = SerializationHelper.readSafeUTF(in);
                xLabel = SerializationHelper.readSafeUTF(in);
                yLabel = SerializationHelper.readSafeUTF(in);
                referenceLine = SerializationHelper.readSafeUTF(in);
            } catch (EOFException e) {
                // squash
            }
        }
    }
}
