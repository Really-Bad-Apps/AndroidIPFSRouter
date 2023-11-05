# AndroidIPFSRouter

AndroidIPFSRouter is a lightweight Android library for interacting with the InterPlanetary File System (IPFS). It enables mobile applications to select the fastest available IPFS gateway and convert `ipfs://` style links to point to that gateway, thus optimizing content fetching from IPFS networks.

## Features

- Fetching the fastest available IPFS gateway.
- Health checking of IPFS gateways.
- Conversion of `ipfs://` and `ipns://` links to use the selected gateway.
- Handling of CID (Content Identifier) based links.
- Easy integration into any Android project.

## Usage

### Checking for the Fastest Gateway

```kotllin
// Perform a node check to update the health status and speed of the gateways
suspend fun checkGateways() {
    nodeCheck { nodeList ->
        // nodeList will contain the updated list of nodes with their health status and speed
    }
}

// Retrieve the fastest gateway after performing the health check
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


### Contribution

If you would like to contribute to the project, please fork the repository, make your changes, and submit a pull request.

### License

AndroidIPFSRouter is released under the MIT License.
