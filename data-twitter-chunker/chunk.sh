# java -cp \
# 	target/uber-data-twitter-chunker-1.0.0.jar \
# 	org.swtk.data.twitter.chunk.Runner \
# 	"/Users/craigtrim/data/Data/twitter/dpev441.innovate.ibm.com/" \
# 	"/Users/craigtrim/data/Data/twitter/output/" \
# 	"250000" \
# 	"GNIP"

java -cp \
	target/uber-data-twitter-chunker-1.0.0.jar \
	org.swtk.data.twitter.chunk.Runner \
	"/Users/craigtrim/data/Data/twitter/output-1/" \
	"/Users/craigtrim/data/Data/twitter/output-2/" \
	"250000" \
	"CANNONICAL"
