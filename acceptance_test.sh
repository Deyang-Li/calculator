#!/bin/bash
test $(curl 178.238.231.36:8765/sum?a=1\&b=2) -eq 3