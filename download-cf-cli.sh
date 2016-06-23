#!/bin/bash

echo "Downloading CF CLI"
wget -O cf.tgz https://cli.run.pivotal.io/stable?release=linux32-binary&version=6.19.0&source=github-rel && tar xzvf cf.tgz
