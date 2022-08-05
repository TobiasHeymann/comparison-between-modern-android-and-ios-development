struct Plant: Serializable {
    let metadata: PlantMetadata
    let facts: [Fact]
    let content: [Content]
    static let COLLECTION_NAME = "plants"
    static func toObject(map: [String: Any]) -> Plant? {
        if map["metadata"] == nil || map["content"] == nil {
            return nil
        }

        let metadata = PlantMetadata.toObject(map: map["metadata"]! as! [String: Any])
        var facts = [Fact]()
        for item in map["facts"]! as! [[String: Any]] {
            if let it = Fact.toObject(map: item) {
                facts.append(it)
            }
        }

        var content = [Content]()
        for item in map["content"]! as! [[String: Any]] {
            if let it = Content.toObject(map: item) {
                content.append(it)
            }
        }

        return metadata == nil ? nil : Plant(metadata: metadata!, facts: facts, content: content)
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
