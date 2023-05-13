#!/usr/bin/bash

for feature in "$@"
do
	mkdir "feature_$feature"
	cd "feature_$feature"
	mkdir data ui
	cd ..
done
