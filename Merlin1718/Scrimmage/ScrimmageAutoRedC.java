package org.firstinspires.ftc.teamcode.team.Merlin1718.Scrimmage;

import android.hardware.TriggerEventListener;

import com.qualcomm.robotcore.eventloop.SyncdDevice;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.sql.Driver;

@Autonomous(name = "RedC", group = "Scrimmage")
//@Disabled //Uncomment this if it is not wanted on the phone
public class ScrimmageAutoRedC extends ScrimmageMeathods {

    public void init(){//This only runs once
        super.init();//Initializing everything needed
    }
    @Override
    public void init_loop(){//Tis runs many time during the init phase
    }
    @Override
    public void start(){}//This runs when the start button is pressed

    private String color = "red";
    private double driveForwardToKnockDistance;
    private double driveBackToKnockDistance;
    private double driveDistanceToRightColumn;
    private double driveDistanceToCenterColumn;
    private double driveDistanceToLeftColumn;
    private double driveForwardToCryptobox;
    private double driveAwayFromCryptobox;



    private String currentCase = "DropSorter";
    private String jewel;
    private String column;
    @Override
    public void loop(){//This runs while opmode is active
        switch (currentCase){
            case "DropSorter":
                boolean doneYet;
                doneYet = sorter("down", color);
                if(doneYet){
                    currentCase = "IDCryptographPicAndJewelColor";
                }
                break;
            case "IDCryptographPicAndJewelColor":

                break;
            case "KnockForward":
                doneYet = driveBasedOnEncoders(driveForwardToKnockDistance, "Forward");
                if(doneYet){
                    currentCase = "RaiseSorter";
                }
                break;
            case "KnockBack":
                doneYet = driveBasedOnEncoders(driveBackToKnockDistance, "Back");
                if(doneYet){
                    currentCase = "RaiseSorter";
                }
                break;
            case "RaiseSorter":
                doneYet = sorter("up", color);
                if(doneYet && !jewel.equals("back")){
                    currentCase = choseColumnCase(column);
                } else if(doneYet){
                    currentCase = "ToForwardPosition";
                }
                break;
            case "ToForwardPosition":
                doneYet = driveBasedOnEncoders(driveForwardToKnockDistance + driveBackToKnockDistance, "Forward");
                if(doneYet){
                    currentCase = choseColumnCase(column);
                }
                break;
            case "ToRightColumn":
                doneYet = driveBasedOnEncoders(driveDistanceToRightColumn, "Forward");
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;
            case "ToCenterColumn":
                doneYet = driveBasedOnEncoders(driveDistanceToCenterColumn, "Forward");
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;
            case "ToLeftColumn":
                doneYet = driveBasedOnEncoders(driveDistanceToLeftColumn, "Forward");
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;
            case "SpinTo90":
                doneYet = turnToGyroHeading(90,0);
                if(doneYet){
                    currentCase = "DriveForward";
                }
                break;
            case "DriveForward":
                doneYet = driveBasedOnEncoders(driveForwardToCryptobox, "Forward");
                if(doneYet){
                    currentCase = "ReleaseGripper";
                }
                break;
            case "ReleaseGripper":
                doneYet = glyphAuto("open");
                if(doneYet){
                    currentCase = "DriveBack";
                }
                break;
            case "DriveBack":
                doneYet = driveBasedOnEncoders(driveAwayFromCryptobox, "Forward");
                if(doneYet){
                    currentCase = "End";
                }
                break;
            case "End":
                moveMotorsPower(0,0,0,0);
                break;
            default:
                moveMotorsPower(0,0,0,0);
                telemetry.addData("What Are You", " Doing");
                telemetry.update();
        }
    }
    @Override
    public void stop(){}








    private String choseColumnCase(String column){
        switch (column){
            case "right":
                return "ToRightColumn";
            case "center":
                return "ToCenterColumn";
            case "left":
                return "ToLeftColumn";
            default:
                telemetry.addData("Not Left Right Or", " Center");
                return "";
        }
    }

}
