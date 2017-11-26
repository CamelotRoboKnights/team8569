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
        super.initCamera();
    }
    @Override
    public void init_loop(){//Tis runs many time during the init phase
    }
    @Override
    public void start(){}//This runs when the start button is pressed

    private String color = "red";
    private double driveForwardToKnockDistance;
    private double driveBackToKnockDistance;
    private double spinRightToKnockOffRightJewel = 5; //= 10;
    private double spinLeftToKnockOffLeftJewel  = -5;// = -10;
    private double driveDistanceToRightColumn = 28;
    private double driveDistanceToCenterColumn = 35;
    private double driveDistanceToLeftColumn = 45;
    private double driveForwardToCryptobox = 10;
    private double driveAwayFromCryptobox = 2;



    private String currentCase = "DropSorter";
    private String jewel = "null";
    private String column = "null";
    @Override
    public void loop(){//This runs while opmode is active
        telemetry.addData("CurrentCase", currentCase);
        telemetry.addData("jewel", jewel);
        telemetry.addData("colum", column);
        switch (currentCase){
            case "DropSorter":
                boolean doneYet;
                doneYet = sorter("up");
                if(doneYet){
                    currentCase = "IDCryptographPicAndJewelColor";
                }
                break;
            case "IDCryptographPicAndJewelColor":
                jewel = super.jewelColor();
                column = "RIGHT";
                //column = super.key();
                if(!jewel.equals("null") && !column.equals("null")){
                    if(jewel.equals("red")){
                        currentCase = "SpinRightToKnockOffRightJewel";
                    }
                    else {
                        currentCase = "SpinLeftToKnockOffLeftJewel";
                    }
                }
                break;
            case "SpinRightToKnockOffRightJewel":
                doneYet = turnToGyroHeading(spinRightToKnockOffRightJewel, revOrientation());
                if(doneYet){
                    //currentCase = "RaiseSorter";
                    currentCase = "End";
                }
                break;
            case "SpinLeftToKnockOffLeftJewel":
                doneYet = turnToGyroHeading(spinLeftToKnockOffLeftJewel, revOrientation());
                if(doneYet){
                    // currentCase = "RaiseSorter";
                    currentCase = "End";
                }
                break;
            case "RaiseSorter":
                doneYet = sorter("down");
                if(doneYet){
                    currentCase = "SpinBackToStartingPosition";
                }
                break;
            case "SpinBackToStartingPosition":
                doneYet = turnToGyroHeading(0,revOrientation());
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
                doneYet = turnToGyroHeading(90,revOrientation());
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
            case "RIGHT":
                return "ToRightColumn";
            case "CENTER":
                return "ToCenterColumn";
            case "LEFT":
                return "ToLeftColumn";
            default:
                telemetry.addData("Not Left Right Or", " Center");
                return "";
        }
    }

}
