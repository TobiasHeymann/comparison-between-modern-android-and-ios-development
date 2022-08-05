struct Content: Serializable {
    let type: ContentType
    let content: String
    static func toObject(map: [String: Any]) -> Content? {
        return map["type"] == nil || map["content"] == nil ? nil : Content(type: ContentType(rawValue: map["type"]! as! String)!, content: map["content"]! as! String)
    }
}

