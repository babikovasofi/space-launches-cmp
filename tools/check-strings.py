#!/usr/bin/env python3
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
RESOURCES = ROOT / "shared/src/commonMain/composeResources"
BASE_LOCALE = "values"


def keys(path):
    return set(re.findall(r'name="([^"]+)"', path.read_text(encoding="utf-8")))


def main():
    base_file = RESOURCES / BASE_LOCALE / "strings.xml"
    if not base_file.exists():
        print(f"нет базовой локали: {base_file}")
        return 1

    base_keys = keys(base_file)
    other_files = sorted(RESOURCES.glob("values-*/strings.xml"))
    if not other_files:
        print("второй локали нет — сверять нечего")
        return 1

    failed = False
    for path in other_files:
        locale = path.parent.name
        missing = sorted(base_keys - keys(path))
        extra = sorted(keys(path) - base_keys)
        if missing or extra:
            failed = True
            print(f"{locale}: расхождений {len(missing) + len(extra)}")
            for key in missing:
                print(f"  нет в {locale}: {key}")
            for key in extra:
                print(f"  нет в {BASE_LOCALE}: {key}")
        else:
            print(f"{locale}: 0 расхождений, ключей {len(base_keys)}")

    return 1 if failed else 0


if __name__ == "__main__":
    sys.exit(main())
