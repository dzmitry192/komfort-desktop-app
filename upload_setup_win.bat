@echo off

@REM SET "filename=\Users\daniil\Downloads\some.dmg"
SET "filetype=application\octet-stream"
SET "url=http://localhost/desktop/mac/1.0"

curl "%url%" "--form" "setup=@%1;type=%filetype%" "-H" "Authorization: Basic qweqwew"