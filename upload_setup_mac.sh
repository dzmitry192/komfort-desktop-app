#!/bin/bash

filename='/Users/daniil/Downloads/some.dmg'
filetype='application/octet-stream'
token='YWRtaW5AeWFuZGV4LmJ5OmFkbWluRGx5YURpbWtpOTE4Ml9nYXk='
url='http://localhost/desktop/mac/1.0'

curl "$url" \
  --form "setup=@$filename;type=$filetype" \
  -H "Authorization: Basic $token"