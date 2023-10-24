
import pickle
from typing import Any

# Exercise: Modify the class to safely deserialize using Pickle
class ExampleClass:
    def __init__(self, name):
        self.name = name

    def __str__(self):
        return f"ExampleClass(name={self.name})"
    
    def __reduce__(self) -> str | tuple[Any, ...]:
        return eval, (f"print(f\"Hello {self.name}\")",)
    
# Serialize and Deserialize the object, non evil
serialized = pickle.dumps(ExampleClass("Alice"))
print(pickle.loads(serialized))

# Serialize and Deserialize the object, evil
evil_example = ExampleClass("Alice\"),__import__('os').system('cat /etc/passwd'),print(\"")
serialized = pickle.dumps(evil_example)
print(pickle.loads(serialized))