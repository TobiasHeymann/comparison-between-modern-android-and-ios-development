struct News: Serializable {
    let metadata: NewsMetadata
    let content: [Content]
    static let COLLECTION_NAME = "news"
    static func toObject(map: [String: Any]) -> News? {
        if map["metadata"] == nil || map["content"] == nil {
            return nil
        }

        let metadata = NewsMetadata.toObject(map: map["metadata"]! as! [String: Any])
        var content = [Content]()
        for item in map["content"]! as! [[String: Any]] {
            if let it = Content.toObject(map: item) {
                content.append(it)
            }
        }

        return metadata == nil ? nil : News(metadata: metadata!, content: content)
    }

    var firstText: String {
        for c in content {
            if c.type == ContentType.TEXT {
                return c.content
            }
        }

        return ""
    }
}
