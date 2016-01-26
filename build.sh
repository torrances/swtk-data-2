pushd data-imdb-builder			&& mvn clean install && popd
pushd data-wiktionary-builder	&& mvn clean install && popd
pushd data-wordnet-builder		&& mvn clean install && popd
pushd data-twitter-builder		&& mvn clean install && popd

pushd data-ccr-munge			&& mvn clean install && popd
pushd data-drwho-scrape			&& mvn clean install && popd
pushd data-wordnik-api			&& mvn clean install && popd
