package org.firstinspires.ftc.teamcode.team.Merlin2020;

import android.os.Environment;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.MasterTeleOp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@TeleOp(name = "LogTest")
public class MaxTeleOp extends MasterTeleOp {

    private ElapsedTime time;
    private FileWriter writer;

    @Override
    public void init()
    {
        try {
            this.writer = new FileWriter(Environment.getExternalStorageDirectory().getPath() + "/FIRST/DataLogger/zlog.txt");
            this.writer.write("INIT : ");
        } catch (IOException e) {
            this.telemetry.addData("Caught exception", e);
            this.telemetry.update();
        }

        this.time = new ElapsedTime();

        super.init();
    }

    @Override
    public void loop()
    {
        try {
            this.writer.append("LOOP : TIME = " + this.time.milliseconds() + '\n');
            this.writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.loop();
    }

    @Override
    public void stop()
    {
        try {
            this.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
