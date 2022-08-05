struct Fact: Serializable {
    let icon: FactIconType
    let text: String
    static func toObject(map: [String: Any]) -> Fact? {
        return map["icon"] == nil || map["text"] == nil ? nil : Fact(icon: FactIconType(rawValue: map["icon"]! as! String)!, text: map["text"]! as! String)
    }
}
