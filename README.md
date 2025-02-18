# AndroidIPFSRouter

AndroidIPFSRouter is a lightweight Android library for interacting with the InterPlanetary File System (IPFS). It enables mobile applications to select the fastest available IPFS gateway and convert `ipfs://` style links to point to that gateway, thus optimizing content fetching from IPFS networks.

## Features

- Fetching the fastest available IPFS gateway.
- Health checking of IPFS gateways.
- Conversion of `ipfs://` and `ipns://` links to use the selected gateway.
- Handling of CID (Content Identifier) based links.
- Easy integration into any Android project.

## Installation

### Gradle setup

Add jitpack.io to your repository to your project, for example:

```groovy
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
```

Add the following dependency to your module's `build.gradle` file:

```groovy
    dependencies {
        implementation 'com.github.Really-Bad-Apps:AndroidIPFSRouter:<LATEST_RELEASE>'
    }
```


## Usage

### Checking for the Fastest Gateway

```kotlin
// For Kotlin coroutines usage:
suspend fun checkGateways() {
    nodeCheck { nodeList ->
        // nodeList will contain the updated list of nodes with their health status and speed
    }
}

// For Java usage:
CompletableFuture<Unit> future = nodeCheckAsync { nodeList ->
    // nodeList will contain the updated list of nodes with their health status and speed
}

// After performing the health check, get the fastest node:
val fastestNode = getFastestNode()
```

### Fetching Data from IPFS

To fetch data from IPFS using the fastest gateway, you first need to transform the IPFS link and then use that transformed URL to fetch the data.

```kotlin
val ipfsLink = "ipfs://your-content-hash"
val fastestNode = getFastestNode()
val transformedUrl = transform(ipfsLink, fastestNode)

// Use transformedUrl to fetch the data from IPFS
```

### Adding Custom Gateways

The library comes with a default list of IPFS gateways, but you can add your own gateways to be included in the health checks:

The default gateways include: w3s.link, dweb.link, cf-ipfs.com, 4everland.io, gw3.io, storry.tv, and nftstorage.link. 
All default gateways use HTTPS and are configured as remote nodes (remote=true).

> **Understanding Remote Nodes**: 
> A "remote" node typically refers to a public IPFS gateway that uses special security features. When remote=true:
> - The gateway uses HTTPS for secure connections
> - For certain IPFS content, it will use subdomain-style URLs (like `bafybei...ipfs.dweb.link`) instead of path-style URLs
> - This subdomain approach helps enforce browser security rules for each piece of content
>
> Set remote=false for local gateways (like `localhost`) or private gateways where you don't need these security features.

```kotlin
// Add a custom gateway
addNode(Node(
    host = "your-gateway.com",
    remote = true,  // Use HTTPS and subdomain security (for public gateways)
    hot = false     // true if this is a "hot" node that should get a speed bonus
))

// You might want to add local or private gateways
addNode(Node(
    host = "localhost",
    port = 8080,
    remote = false  // Use HTTP, no subdomain security (for local/private nodes)
))
```

Adding nodes is useful when you:
- Have your own IPFS gateway
- Want to use a local node
- Know of other public gateways not in the default list
- Need to use specific gateways for your use case

### Contribution

If you would like to contribute to the project, please fork the repository, make your changes, and submit a pull request.

### License

AndroidIPFSRouter is released under the MIT License.
