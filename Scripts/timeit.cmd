@echo off
powershell -Command "& {Measure-Command {%*}}"
