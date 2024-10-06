
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrain extends SubsystemBase {
  private Spark motorLeft1 = new Spark(DriveTrainConstants.MOTOR_LEFT_1);
  private Spark motorLeft2 = new Spark(DriveTrainConstants.MOTOR_LEFT_1);
  private Spark motorRight1 = new Spark(DriveTrainConstants.MOTOR_LEFT_1);
  private Spark motorRight2 = new Spark(DriveTrainConstants.MOTOR_LEFT_1);


  public void turnLeftMotors (double speed) {
    speed *= DriveTrainConstants.SPEED_CONST;
    
    motorLeft1.set(-speed);
    motorLeft2.set(-speed);
  }

  public void turnRightMotors (double speed) {
    speed *= DriveTrainConstants.SPEED_CONST;
    
    motorRight1.set(speed);
    motorRight2.set(speed);
  }

  public DriveTrain() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
