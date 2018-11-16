#!/bin/sh

build_react(){
	cd ${WORKSPACE}/cashloan-manage/src/main
	if [ -f node_modules.zip ]
	then
		unzip -oq node_modules.zip && chmod +x node_modules/.bin/* && npm run deploy
	else
		npm i && npm run deploy
	fi
}


build_war(){
	cd ${WORKSPACE}
	mvn clean package -Dmaven.test.skip=true -e -U -P prod
}

build_react

build_war
