#!/bin/bash

filename='/Users/daniil/Downloads/some.dmg'
filetype='application/octet-stream'
url='http://localhost/desktop/mac/1.0'

curl "$url" \
  --form "setup=@$filename;type=$filetype" \
  -H "Authorization: Basic werewrew"