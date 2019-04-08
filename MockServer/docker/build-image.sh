#!/bin/bash

cp ../mock-smtp-server/MockMock.jar .

docker build --tag mock-smtp-server .
