// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  private WPI_TalonSRX armMotor;

  private double kP = 0.1;
  private double kI = 0.01;
  private double kD = 0.1;

  private double integralSum = 0;
  private double lastError = 0;

  private double targetAngle = 0;

  public Arm() {
    armMotor = new WPI_TalonSRX(Constants.ARM_MOTOR_PORT);

    armMotor.configFactoryDefault();
    armMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
    
    armMotor.config_kP(0, kP, 10);
    armMotor.config_kI(0, kI, 10);
    armMotor.config_kD(0, kD, 10);

    armMotor.configAllowableClosedloopError(0, 10, 10);

    armMotor.setSelectedSensorPosition(0);
  }

  public void moveToAngle(double targetAngle) {
    this.targetAngle = targetAngle;
    double targetPositionTicks = (targetAngle / 360.0) * 4096.0;
    armMotor.set(ControlMode.Position, targetPositionTicks);
  }

  public double getCurrentAngle() {
    return (armMotor.getSelectedSensorPosition() / 4096.0) * 360.0;
  }

  public void updatePID() {
    double setpoint = armMotor.getSelectedSensorPosition();
    double currentAngle = (setpoint / 4096.0) * 360.0;
    double error = targetAngle - currentAngle;
    integralSum += error;
    double derivative = error - lastError;
    lastError = error;

    double output = kP * error + kI * integralSum + kD * derivative;

    armMotor.set(ControlMode.PercentOutput, output);
  }

  public void stop() {
    armMotor.set(ControlMode.PercentOutput, 0);
  }
  @Override
  public void periodic() {
    updatePID();
  }
}
